import axios from "axios";
import { getCurrentUserDetails } from "./helper-service";
import { BASE_URL } from "./user-service";


export const addCommentToPost = (postId,comment) =>{
    return axios.post(BASE_URL+"/posts/"+postId+"/comments",comment,{
        headers : {
            "Authentication" : "Bearer "+ getCurrentUserDetails().token
        }
    });
}