
export const login = (loginData) =>{

    if(isLoggedIn()==false){
        localStorage.setItem('data', JSON.stringify(loginData));
    }

}

export const isLoggedIn = () =>{

    if(localStorage.getItem('data')){
        return true;
    }
    else{
        return false;
    }

}

export const logoutUser = () =>{

    if(isLoggedIn()){
        localStorage.removeItem('data');
    }

}

export const getCurrentUserDetails = () =>{
    
    const data = localStorage.getItem('data');

    if(isLoggedIn()){
        return JSON.parse(data);
    }
}