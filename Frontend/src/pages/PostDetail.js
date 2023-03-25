import React, { createContext, useContext, useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { AppContext } from '../App';
import { addCommentToPost } from '../services/comment-service';
import { getCurrentUserDetails } from '../services/helper-service';
import { getPostById } from '../services/post-service';


const PostDetail = () => {

  const appContext = useContext(AppContext);

  const params = useParams();

  const [post, setPost] = useState({});
  const [comment, setComment] = useState({
    content: "",
    username: ""
  });

  useEffect(() => {

    getPostById(params.id)
      .then((response) => {
        console.log(response);
        setPost(response);

        appContext.setComments(response.comments);
      })
      .catch((error) => {
        console.log(error);
        alert("Something went wrong. Please, Try in some time.");
      });

  }, [appContext]);


  const addComment = (e) => {

    e.preventDefault();

    addCommentToPost(params.id, comment)
      .then((response) => {
        console.log(response);
        alert("Comment Added Successfully");
        getPostById(params.id)
          .then((response) => {
            console.log(response);
            setPost(response);

            appContext.setComments(response.comments);
          })
          .catch((error) => {
            console.log(error);
            alert("Something went wrong. Please, Try in some time.");
          });

      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div className='container text-left w-80 rounded border-0 shadow-lg p-3 mb-5 bg-white rounded' style={{ marginTop: "50px" }}>

      <h1 className="display-4 m-3">{post.title}</h1>

      <div className="card w-80">
        <div className="card-body w-70">
          <h6 className="card-subtitle mb-2 text-muted">Posted By : {post?.user?.name} - {post?.user?.email}</h6>
          <h6 className="card-subtitle mb-2 text-dark">Category : {post?.category?.categoryTitle}</h6>

          <p className='card-text mt-4 mb-4' dangerouslySetInnerHTML={{ __html: post.content }}></p>
        </div>
      </div>

      <div className='container'>
        <div className='card'>
          <p className="card-header">Comments ({post?.comments?.length})</p>

          <div className='add-comment w-60'>
            <form onSubmit={(e) => addComment(e)} className="p-1 m-2">
              <input type="text"
                className='w-100'
                placeholder='Add Your Comment'
                value={comment.content}
                onChange={(e) => setComment({ ...comment, "content": e.target.value })}
              />

              {
                (appContext.loggedIn == true)
                  ?
                  <button type="submit" className="btn btn-success border-0 rounded-0 m-1 mt-3">Add Comment</button>
                  :
                  <p class="alert alert-danger">Login First To Comment On This Post...</p>
              }
            </form>
          </div>

          <div class="card text-left mt-4" >
            <div class="card-header">
              Previous Comments
            </div>
            <ul class="list-group list-group-flush">
              {
                appContext.comments.map((comment, index) => {
                  return (<div>
                    <li class="list-group-item"><span><b>By : </b> {comment.username}</span> <span>{comment.content}</span></li>
                  </div>)
                })
              }
            </ul>
          </div>
        </div>
      </div>
    </div>
  )
}

export default PostDetail;
