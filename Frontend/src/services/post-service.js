import axios from "axios";
import { getCurrentUserDetails } from "./helper-service";
import { BASE_URL } from "./user-service";

export const getAllPosts = (pageNumber, pageSize) => {
    return axios.get(BASE_URL+"/posts?pageNumber="+ pageNumber+"&pageSize="+pageSize+"&sortBy=title").then((response) => response.data);
}

export const getPostById = (postId) => {
    return axios.get(BASE_URL + "/posts/" + postId).then((response) => response.data);
}

export const addNewPost = (newPost) => {
    return axios.post(BASE_URL + "/user/" + newPost.userId + "/category/" + newPost.categoryId + "/posts", newPost, {
        headers:
        {
            "Authentication": "Bearer " + getCurrentUserDetails().token
        }
    });
}

export const getUserPosts = (userId) => {
    return axios.get(BASE_URL + "/user/" + userId + "/posts").then((response) => response.data);
}

export const deletePostById = (postId) =>{
    return axios.delete(BASE_URL+"/posts/"+postId,
    {
        headers:
        {
            "Authentication": "Bearer " + getCurrentUserDetails().token
        }
    }
    );
}

export const updateThePost = (postId, post) =>{
    return axios.put(BASE_URL+"/posts/"+postId, post, 
    {
        headers:
        {
            "Authentication": "Bearer " + getCurrentUserDetails().token
        }
    }
    );
}