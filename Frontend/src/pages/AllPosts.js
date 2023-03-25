import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { getAllPosts } from '../services/post-service';

const AllPosts = () => {

  const [posts, setPosts] = useState([]);

  const [allPostData, setAllPostData] = useState({});

  const [totalPages, setTotalPages] = useState(0);

  const navigate = useNavigate();

  useEffect(() => {

    getAllPosts(0, 4)
      .then((response) => {
        setPosts(response.postDTOs);
        setAllPostData(response);
        setTotalPages(response.totalPages);
      })
      .catch((error) => {
        console.log(error);
        alert("Something went wrong. Please, Try Again in some time");
      });

  }, []);

  const changePage = (pageNumber, pageSize) => {

    window.scrollTo({top:0});

    getAllPosts(pageNumber, pageSize)
      .then((response) => {
        setPosts(response.postDTOs);

        console.log(response);
        setAllPostData(response);
      })
      .catch((error) => {
        console.log(error);

        alert("Error while loading page");
      })

  }

  return (
    <div className='container w-70 rounded border-0 shadow-lg p-3 mb-5 bg-white rounded' style={{ marginTop: "50px" }}>

      {
        posts.map((post, index) => {
          return (
            <div className="card m-3" key={index}>
              <div className="card-body">
                <h5 className="card-title m-2 fw-bold">{post.title}</h5>
                <h6 className="card-subtitle m-2 text-muted">Posted By : {post.user.name} - {post.user.email}</h6>
                <h6 className="card-subtitle m-2 text-dark">Category : {post.category.categoryTitle}</h6>

                <button className="btn btn-dark mt-4 mx-2" onClick={() => navigate("/posts/"+post.postId)}>Read More</button>
              </div>
            </div>
          )
        })
      }

      <div className='container'>
        <nav aria-label="Page navigation">
          <ul className="pagination pagination-lg justify-content-center">

          {
            (allPostData.pageNumber>0) ? <li className="page-item"><a className="page-link" onClick={()=> changePage(allPostData.pageNumber-1,3)}>Previous</a></li> : <p></p>
          }
            

            {
              [...Array(totalPages)].map((page, index)=>{
                return (
                  <li className="page-item" ><a className="page-link"  onClick={()=> changePage(index,3)}>{index+1}</a></li>
                )
              })
            }


            { (allPostData.lastPage==true) ? <p></p> : <li className="page-item"><a className="page-link" onClick={()=> changePage(allPostData.pageNumber+1,3)}>Next</a></li>}
          </ul>
        </nav>
      </div>
    </div>
  )
}

export default AllPosts
