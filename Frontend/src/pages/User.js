import React, { useContext } from 'react'
import { AppContext } from '../App'
import {Outlet, useNavigate} from 'react-router-dom';
const User = () => {

    const appContext = useContext(AppContext);

    const navigate = useNavigate();

  return (
    <div className='mx-auto my-auto'>
      {
        (appContext.loggedIn==true)
        ?
        <Outlet />
        :
        <div className='container text-center mx-auto my-auto'>
            <h1 className='display-5 d-flex justify-content-center mt-3' style={{alignSelf:"center"}}>Login First To Access This Page</h1>

            <button className='btn btn-success m-4 p-2 rounded-0' onClick={()=> navigate("/login")}> Login</button>
        </div>
      }
    </div>
  )
}

export default User
