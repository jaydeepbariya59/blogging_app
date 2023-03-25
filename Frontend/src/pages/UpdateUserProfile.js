import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import { getCurrentUserDetails } from '../services/helper-service';
import { updateTheUser, userLogin } from '../services/user-service';

const UpdateUserProfile = () => {

    const [userData, setUserData] = useState({
        name : "",
        email : "",
        password : "",
        about : ""
    });


    useEffect(()=>{
        setUserData(getCurrentUserDetails().userDetails);
    },[]);

    const params = useParams();

    const handleChange = (e, field) =>{
        setUserData({...userData, [field] : e.target.value});
    }

    const resetForm = () =>{
        setUserData({
            name : "",
            email : "",
            password : "",
            about : ""
        });
    }

    const updateUser = (e) =>{

        updateTheUser(params.id, userData)
            .then((response) => {
                console.log(response);
                alert("User updated successfully");
            })
            .catch((error) =>{
                console.log(error);
                alert("User not updated...");
            })

    }

    return (
    <form onSubmit={(e) => updateUser(e)} className='container w-50 rounded border-0 shadow-lg p-3 mb-5 bg-white rounded' style={{ marginTop: "50px" }}>

    <h1 className="display-5 text-center">Update User</h1>

    <div className="form-group mt-3">
      <label htmlFor="name">Name : </label>
      <input type="text" className="form-control" id="name" aria-describedby="name" placeholder="Enter Your Name"
        value={userData.name}
        onChange={(e) => handleChange(e, "name")}
      />
    </div>
    <div className="form-group mt-3">
      <label htmlFor="email">Email : </label>
      <input type="email" className="form-control" id="email" aria-describedby="email" placeholder="Enter Your Email"
        value={userData.email}
        onChange={(e) => handleChange(e, "email")}
      />
    </div>
    <div className="form-group mt-3">
      <label htmlFor="password">Password : </label>
      <input type="password" className="form-control" id="password" aria-describedby="password" placeholder="Enter Your Password"
        value={userData.password}
        onChange={(e) => handleChange(e, "password")}
      />
    </div>
    <div className="form-group">
      <label htmlFor="about">About : </label>
      <textarea className="form-control" id="about" rows="4"
          value={userData.about}
          onChange={(e) => handleChange(e, "about")}
      ></textarea>
    </div>
    <div className="row mt-4">
      <div className='col text-center'>
        <button type="submit" className="btn btn-primary border-0">Submit</button>
      </div>

      <div className="col text-center">
        <button type="reset" className="btn btn-danger border-0" onClick={resetForm}>Reset</button>
      </div>
    </div>

  </form>
  )
}

export default UpdateUserProfile
