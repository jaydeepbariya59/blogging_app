import React, {  useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { getCurrentUserDetails } from '../services/helper-service';
import { deletePostById, getUserPosts } from '../services/post-service';
import { AppContext } from '../App';
const UserPosts = () => {

  const [userPosts, setUserPosts] = useState([]);

  const navigate = useNavigate();


  useEffect(() => {
    getUserPosts(getCurrentUserDetails().userDetails.id)
      .then((response) => {
        console.log(response);
        setUserPosts(response);
      })
      .catch((error) => {
        console.log(error);
        alert("Error Fetching Posts of User");
      });
  }, []);

  const deletePost = (postId) => {

    deletePostById(postId)
      .then((response) => {
        console.log(response);
        alert("Post Deleted Successfully");

        navigate("/user/posts");
      })
      .catch((error) => {
        console.log(error);
        alert("Error in Deleting the Post");
      });


  }

  return (

    <div>
          <div className='container w-70 rounded border-0 shadow-lg p-3 mb-5 bg-white rounded' style={{ marginTop: "50px" }}>

            <h1 className="display-4 text-center">My Posts</h1>

            {
              userPosts.map((post, index) => {
                return (
                  <div class="card m-3" key={index}>
                    <div class="card-body">
                      <h5 class="card-title m-2 fw-bold">{post.title}</h5>
                      <h6 class="card-subtitle m-2 text-muted">Posted By : {post.user.name} - {post.user.email}</h6>
                      <h6 class="card-subtitle m-2 text-dark">Category : {post.category.categoryTitle}</h6>
                      <div className='row'>
                        <button className="btn btn-success m-4 col" onClick={()=> navigate("/posts/"+post.postId)}>Read More</button>
                        <button className="btn btn-warning m-4 col" onClick={()=> navigate("/user/update-post/"+post.postId)}>Update Post</button>
                        <button className="btn btn-danger m-4 col" onClick={() => deletePost(post.postId)}>Delete Post</button>
                      </div>
                    </div>
                  </div>
                )
              })
            }
          </div>
      
    </div>
  )
}

export default UserPosts
