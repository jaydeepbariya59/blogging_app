import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { userRegistration } from '../services/user-service';

const Signup = () => {

  const [userData, setUserData] = useState({
    name: "",
    email: "",
    password: "",
    about: ""
  });


  const navigate = useNavigate();

  const [errors, setErrors] = useState({});

  const handleChange = (e, field) => {
    setUserData({ ...userData, [field]: e.target.value });
  }

  const registerUser = (e) => {
    e.preventDefault();

  // form validation
  if (userData.name == "") {
    setErrors({ ...errors, name: "Name is required" });
    return ;
  }
  else if (userData.name.length > 20) {
    setErrors({ ...errors, name: "Name should not be more than 20 characters" });
    return ;
  }

  if (userData.email == "") {
    setErrors({ ...errors, email: "Email is required" });
    return ;

  }
  // else if ((/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g).test(userData.email)) {
  //   setErrors({ ...errors, email: "Invalid Email Format" });
  //   return ;

  // }


  if (userData.password == "") {
    setErrors({ ...errors, password: "Password is required" });
    return ;

  }
  else if ((userData.password.length) > 10){
    setErrors({ ...errors, password: "Invalid Password Format. Maximum 10 characters allowed." });
    return ;

  }

  if (userData.about == "") {
    setErrors({ ...errors, about: "About is required" });
    return ;

  }
  else if ((userData.about.length) > 1000){
    setErrors({ ...errors, password: "Invalid About Format. Maximum 1000 characters allowed." });
    return ;

  }

    userRegistration(userData)
      .then((response) => {
        console.log(response);
        alert("User Registration Successful..");
        alert("Now, Please Login..");

        navigate("/login");
      })
      .catch((error) => {
        console.log(error);
        alert("Something went wrong. Please, Try Again In Some Time.");
      });
  }


  const resetForm = () => {
    setUserData({
      name: "",
      email: "",
      password: "",
      about: ""
    });
  }
  return (
    <form onSubmit={(e) => registerUser(e)} className='container w-50 rounded border-0 shadow-lg p-3 bg-white rounded' style={{ marginTop: "50px" }}>

      <h1 className="display-4 text-center">Register User</h1>

      <div className="form-group mt-3">
        <label htmlFor="name">Name : </label>
        <input type="text" className="form-control" id="name" aria-describedby="name" placeholder="Enter Your Name"
          value={userData.name}
          onChange={(e) => handleChange(e, "name")}
        />
        <p className='text-danger'>{errors.name}</p>
      </div>
      <div className="form-group mt-3">
        <label htmlFor="email">Email : </label>
        <input type="text" className="form-control" id="email" aria-describedby="email" placeholder="Enter Your Email"
          value={userData.email}
          onChange={(e) => handleChange(e, "email")}
        />
      </div>
      <p className='text-danger'>{errors.email}</p>
      <div className="form-group mt-3">
        <label htmlFor="password">Password : </label>
        <input type="password" className="form-control" id="password" aria-describedby="password" placeholder="Enter Your Password"
          value={userData.password}
          onChange={(e) => handleChange(e, "password")}
        />
        <p className='text-danger'>{errors.password}</p>
      </div>
      <div className="form-group">
        <label htmlFor="about">About : </label>
        <textarea className="form-control" id="about" rows="4" placeholder='Enter About Yourself'
          value={userData.about}
          onChange={(e) => handleChange(e, "about")}
        ></textarea>
        <p className='text-danger'>{errors.about}</p>

      </div>
      <div className="row mt-4">
        <div className='col text-center'>
          <button type="submit" className="btn btn-primary border-0">Submit</button>
        </div>

        <div className="col text-center">
          <button type="reset" className="btn btn-danger border-0" onClick={resetForm}>Reset</button>
        </div>
      </div>

      <div className='row'>
        <div className='col text-center'>
          <p>Already Have An Account ?? <span className='nav-link'> <Link to="/login">Login First.</Link> </span></p>
        </div>
      </div>
    </form>

  )
}

export default Signup
