import axios from "axios";
import { BASE_URL } from "./user-service";

export const getCategories = () =>{
    return axios.get(BASE_URL+"/categories").then((response)=> response.data);
}