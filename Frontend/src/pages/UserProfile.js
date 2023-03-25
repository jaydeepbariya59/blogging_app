import React, { useEffect, useState } from 'react'
import { getCurrentUserDetails } from '../services/helper-service';
import { useNavigate } from 'react-router-dom';
import { deleteUserById } from '../services/user-service';
const UserProfile = () => {

  const imgUrl = "https://www.kindpng.com/picc/m/780-7804962_cartoon-avatar-png-image-transparent-avatar-user-image.png";

  const navigate = useNavigate();

  const [user, setUser] = useState({});

  useEffect(() => {
    setUser(getCurrentUserDetails().userDetails);
    console.log(user);
  }, []);

  const deleteUser = () => {

    deleteUserById(user.id)
      .then((response) => {
        alert("user deleted successfully");
      })
      .catch((error) => {
        alert("user not deleted. something went wrong");
      });


  }

  return (
    <div className='container w-80 rounded border-0 shadow-lg p-3 mb-5 bg-white rounded' style={{ marginTop: "50px" }}>

      <h1 className="display-5 m-3 text-left">Welcome {user.name}</h1>

      <div className='container d-flex justify-content-between'>

        <div>
          <img src={imgUrl} className= "w-100 mx-auto my-3" width={350} height={300} />
        </div>

        <div className='mx-auto my-auto'>
          <p className='fs-5'>Name : {user.name}</p>
          <p className='fs-5'>Email : {user.email}</p>
          <p className='fs-5'>About : {user.about}</p>
        </div>
      </div>

      <div className='d-flex justify-content-around mt-4'>
        <div className='c1'>
          <button className='btn btn-info rounded-0' onClick={() => navigate("/user/user-profile/" + user.id + "/update")}>UPDATE PROFILE</button>
        </div>

        <div className='c2'>
          <button className='btn btn-danger rounded-0' onClick={deleteUser}>DELETE PROFILE</button>
        </div>
      </div>
    </div>
  )
}

export default UserProfile
