import React, { useContext, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AppContext } from '../App';
import { login } from '../services/helper-service';
import { userLogin } from '../services/user-service';

const Login = () => {

  const appContext = useContext(AppContext);

  const [loginData, setLoginData] = useState({
    username: "",
    password: ""
  });

  const [formErrors, setFormErrors] = useState({});

  const navigate = useNavigate();

  const handleChange = (event, field) => {

    setLoginData({ ...loginData, [field]: event.target.value });
    validateForm(loginData);

  }

  const loginUser = (e) => {
    e.preventDefault();


    userLogin(loginData)
      .then((response) => {
        console.log(response);
        alert("Logged In Successfully...");

        login(response);

        appContext.setLoggedIn(true);

        navigate("/user/posts");

      })
      .catch((error) => {
        console.log(error);
        alert("Invalid Credentials");

        navigate("/login");
      })


  }

  const validateForm = (loginData) => {


    if (loginData.username == "") {
      setFormErrors({ ...formErrors, username: "Email is required" });
    }
    else if ((/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i).test(loginData.username)) {
      setFormErrors({ ...formErrors, username: "Enter Valid Email " });
    }

    if (loginData.password == "") {
      setFormErrors({ ...formErrors, password: "Password is required" });
    }
    else if (loginData.password.length < 5) {
      setFormErrors({ ...formErrors, password: "Password should be more than 5 characters" });

    }

  }

  return (
    <form onSubmit={(e) => loginUser(e)} className='container w-50 rounded border-0 shadow-lg p-3 bg-white rounded' style={{ marginTop: "50px" }}>

      <h1 className="display-4 text-success text-center">Login</h1>

      <div className="form-group mt-3">
        <label htmlFor="email">Email Address : </label>
        <input type="text" className="form-control my-2" id="email" aria-describedby="email" placeholder="Enter Your Email"
          value={loginData.username}
          onChange={(e) => handleChange(e, "username")}
        />
        <p className='text-danger'>{formErrors.username}</p>
      </div>
      <div className="form-group mt-3">
        <label htmlFor="password">Password : </label>
        <input type="password" className="form-control my-2" id="password" aria-describedby="password" placeholder="Enter Your Password"
          value={loginData.password}
          onChange={(e) => handleChange(e, "password")}
        />
        <p className='text-danger'>{formErrors.password}</p>
      </div>

      <div className="row mt-4 my-2">
        <div className='col text-center'>
          <button type="submit" className="btn btn-primary border-0">Submit</button>
        </div>

        <div className="col text-center">
          <button type="reset" className="btn btn-danger border-0">Reset</button>
        </div>
      </div>

      <div className='row'>
        <div className='col text-center'>
          <p>Do Not Have An Account ?? <span className='nav-link'> <Link to="/signup">Sign Up First.</Link> </span></p>
        </div>
      </div>
    </form>


  )
}

export default Login
