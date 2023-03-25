import JoditEditor from 'jodit-react';
import React, { useEffect, useRef, useState } from 'react'
import { useParams } from 'react-router-dom';
import { getCategories } from '../services/category-service';
import { getCurrentUserDetails } from '../services/helper-service';
import { getPostById, updateThePost } from '../services/post-service';

const UpdatePost = () => {

  const [post, setPost] = useState({
    title: "",
    content: "",
    categoryId: "",
    userId: ""
  });

  const [categories, setCategories] = useState([]);

  const editor = useRef(null);
  const [content, setContent] = useState("");
  

  const params = useParams();

  useEffect(() => {

    getPostById(params.id)
      .then((response) => {
        console.log(response);
        setPost(response);
        setContent(response.content);
      })
      .catch((error) => {
        console.log(error);
      });

    getCategories()
      .then((response) => {
        console.log(response);
        setCategories(response);
      })
      .catch((error) => {
        console.log(error);
        alert("Something went wrong");
      });

    setPost({ ...post, userId: getCurrentUserDetails().userDetails.id });
  }, []);

  const handleChange = (e, field) => {
    setPost({ ...post, [field]: e.target.value });
  }

  const updatePost = (e) => {
      

    e.preventDefault();
    
    setPost({ ...post, content: content });

    updateThePost(params.id, post)
      .then((response) => {
        console.log(response);
        alert("Post updated successfully...");
      })
      .catch((error) => {
        console.log(error);
        alert("Error while updating the post");
      });

  }


  return (
      <form onSubmit={(e) => updatePost(e)} className='container text-left w-80 rounded border-0 shadow-lg p-3 mb-5 bg-white rounded' style={{ marginTop: "50px" }}>

        <h1 className="display-5 text-center m-2">Update Post</h1>

        <div className="form-group mt-3">
          <label htmlFor="title">Post Title : </label>
          <input type="text" className="form-control" id="title" aria-describedby="title" placeholder="Enter Your Post Title"
            value={post.title}
            onChange={(e) => handleChange(e, "title")}
          />
        </div>

        <div className='form-group mt-3'>
          <label htmlFor='category'>Category : </label>
          <select className="form-select"
            onChange={(e) => handleChange(e, "categoryId")}
            value={post.categoryId}>
            {
              categories.map((category, index) => {
                return (
                  <option value={category.categoryId} key={index}>{category.categoryTitle}</option>
                )
              })
            }
          </select>
        </div>

        <div className='form-group mt-3'>

          <label htmlFor='content'>Post Content : </label>
          <JoditEditor
            ref={editor}
            value={content}
            onChange={(newContent) => setContent(newContent)}
          />
        </div>
        <div className="row mt-4">
          <div className='col text-center'>
            <button type="submit" className="btn btn-primary border-0">Submit</button>
          </div>

          <div className="col text-center">
            <button type="reset" className="btn btn-danger border-0">Reset</button>
          </div>
        </div>

      </form>
  )
}

export default UpdatePost
