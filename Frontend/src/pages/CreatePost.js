import React, { useEffect, useRef, useState } from 'react'
import { getCategories } from '../services/category-service';
import JoditEditor from 'jodit-react';
import { getCurrentUserDetails } from '../services/helper-service';
import { addNewPost } from '../services/post-service';

const CreatePost = () => {

    const [content, setContent] = useState("");
    const editor = useRef(null);

    const [newPost, setNewPost] = useState({
        title: "",
        content: "",
        categoryId: "",
        userId : ""
    });

    const addPost = (e) => {

        setNewPost({...newPost, content : content});
        e.preventDefault();

        addNewPost(newPost)
            .then((response) =>{
                console.log(response);
                alert("Post added successfully...");
            })
            .catch((error) =>{
                alert("Something went wrong");
                console.log(error);
            })
    }

    const handleChange = (e, field) => {
        setNewPost({ ...newPost, [field]: e.target.value });
        console.log(newPost);
        console.log(content);
    }

    const [categories, setCategories] = useState([]);

    useEffect(() => {

        setNewPost({...newPost, userId : getCurrentUserDetails().userDetails.id});

        getCategories()
            .then((response) => {
                setCategories(response);
            })
            .catch((error) => {
                console.log(error);
                alert("Something went wrong");
            });
    }, []);

    return (
            <form onSubmit={(e) => addPost(e)} className='container w-100 rounded border-0 shadow-lg p-3 mb-5 bg-white rounded' style={{ marginTop: "50px" }}>

                <h1 className="display-4 text-center">Add Post</h1>

                <div className="form-group mt-3">
                    <label htmlFor="title">Post Title : </label>
                    <input type="text" className="form-control" id="title" aria-describedby="title" placeholder="Enter Your Post Title"
                        value={newPost.title}
                        onChange={(e) => handleChange(e, "title")}
                    />
                </div>

                <div className='form-group mt-3'>
                    <label htmlFor='category'>Category : </label>
                    <select className="form-select" id='category' 
                    onChange={(e)=> handleChange(e, "categoryId")} 
                    value = {newPost.categoryId}>
                        {
                            categories.map((category, index) => {
                                return (
                                    <option key={index} value={category.categoryId}>{category.categoryTitle}</option>
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
                        onChange={(newContent) => {setContent(newContent)}}
                    />
                </div>

                <div className="row mt-4">
                    <div className='col text-center'>
                        <button type="submit" className="btn btn-primary border-0">Submit</button>
                    </div>
                </div>

            </form>
    )
}

export default CreatePost
