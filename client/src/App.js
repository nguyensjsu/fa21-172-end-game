import React, { Fragment } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Catalog from './components/Catalog';
import Navbar from './components/Navbar';
import Register from './components/Register';
import Login from './components/Login';


const App = () => 
(
  <Router>
    <Fragment>
      <Navbar/>
      <section className="container">
        <Routes>
          <Route exact path="/" element={<Register/>}/>
          <Route exact path="/register" element={<Register/>}/>
          <Route exact path="/login" element={<Login/>}/>
          <Route exact path="/catalog" element={<Catalog/>}/>
        </Routes>
      </section>
    </Fragment>
  </Router>
);

export default App;