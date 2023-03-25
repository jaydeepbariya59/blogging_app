import React, { useContext } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { AppContext } from '../App';
import { logoutUser } from '../services/helper-service';

const Navbar = () => {

  const navigate = useNavigate();

  const appContext = useContext(AppContext);


  const logout = () => {
    logoutUser();

    appContext.setLoggedIn(false);

    navigate("/");
  }


  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <Link className="navbar-brand fw-bold mx-3" to="/">BlogApp</Link>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNavDropdown">
        <ul className="navbar-nav">
          <li className="nav-item">
            <Link className="nav-link" to="/">Home</Link>
          </li>
        </ul>
      </div>

      {
        (appContext?.loggedIn == true)
          ?
          <div className='nav-btn mx-4'>
            <button className='btn btn-info mx-4' onClick={() => navigate("/user/posts")}>My Posts</button>
            <button className='btn btn-warning mx-4' onClick={() => navigate("/user/user-profile")}>My Profile</button>
            <button className='btn btn-success mx-4' onClick={() => navigate("/user/new-post")}>New Post</button>
            <button className='btn btn-danger mx-4' onClick={logout}>Logout</button>
          </div>
          :
          <div className='nav-btn mx-4'>
            <button className='btn btn-success mx-4' onClick={() => navigate("/login")}>Login</button>
            <button className='btn btn-warning mx-4' onClick={() => navigate("/signup")}>Sign Up</button>
          </div>
      }
    </nav>
  )
}

export default Navbar
