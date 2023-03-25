import axios from 'axios';
import { getCurrentUserDetails } from './helper-service';

export const BASE_URL  = "http://localhost:5000";

export const userLogin = (loginData) =>{
    return axios.post(BASE_URL+"/auth/login", loginData).then((response) => response.data);
}

export const userRegistration = (userData) =>{
    return axios.post(BASE_URL+"/auth/registerUser", userData).then((response)=> response.data);
}

export const updateTheUser = (userId, userData) => {
    return axios.put(BASE_URL+"/users/"+userId, userData, {
        headers : {
            "Authentication" : "Bearer " + getCurrentUserDetails().token
        }
    })
    .then((response)=> response.data);
}

export const deleteUserById = (userId) =>{
    return axios.delete(BASE_URL+"/users/"+userId, {
        headers : {
            "Authentication" : "Bearer " + getCurrentUserDetails().token
        } 
    })
    .then((response) => response.data);
}