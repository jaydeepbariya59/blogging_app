import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Login from './pages/Login';
import Signup from './pages/Signup';
import AllPosts from './pages/AllPosts';
import { createContext, useEffect, useState } from 'react';
import { isLoggedIn } from './services/helper-service';
import PostDetail from './pages/PostDetail';
import UserProfile from './pages/UserProfile';
import CreatePost from './pages/CreatePost';
import UserPosts from './pages/UserPosts';
import UpdatePost from './pages/UpdatePost';
import UpdateUserProfile from './pages/UpdateUserProfile';
import User from './pages/User';

const AppContext = createContext();

function App() {

  const [loggedIn, setLoggedIn] = useState(false);
  const [comments, setComments] = useState([]);

  useEffect(() => {
    setLoggedIn(isLoggedIn());
  }, []);

  return (
    <AppContext.Provider value={{ loggedIn, setLoggedIn, comments, setComments }}>

      <BrowserRouter>
        <Navbar />

        <Routes>
          <Route path="/" element={<AllPosts />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/posts/:id" element={<PostDetail />} />

          <Route path="/user" element={<User />}>
            <Route path='posts' element={<UserPosts />} />
            <Route path='user-profile' element={<UserProfile />} />
            <Route path='user-profile/:id/update' element={<UpdateUserProfile />} />
            <Route path='new-post' element={<CreatePost />} />
            <Route path='update-post/:id' element={<UpdatePost />} />
          </Route>
        </Routes>
        
      </BrowserRouter>

    </AppContext.Provider>

  );
}

export default App;
export { AppContext };