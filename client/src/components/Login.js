import React, { Fragment, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Login() {
    let navigate = useNavigate();

    const [formData, setFormData] = useState({
        userName: '',
        password: ''
    }); 
    
    const { userName, password } = formData;

    const onChange = e => setFormData({ ...formData, [e.target.name]: e.target.value });

    const onSubmit = async e => {
        e.preventDefault();

        const user = {
            userName,
            password
        }

        try 
        {
            const config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            }

            const body = JSON.stringify(user);

            const res = await axios.post('http://localhost:8080/users/login', body, config);
            
            if(res.data = "login")
            {
                navigate("/catalog");
            }
            
        }
        catch(err)
        {
            console.error(err.response.data);
        }
    }

    return (
        <Fragment>
            <h1 className="large text-primary">Log In</h1>
            <p className="lead"><i className="fas fa-user"></i> Sign Into Your Account</p>
            <form className="form" onSubmit={e => onSubmit(e)}>
                <div className="form-group">
                    <input 
                        type="text" 
                        placeholder="User Name" 
                        name="userName" 
                        value={userName}
                        onChange={e => onChange(e)}
                        required 
                    />
                </div>
                <div className="form-group">
                    <input
                        type="password"
                        placeholder="Password"
                        name="password"
                        value={password}
                        onChange={e => onChange(e)}
                        required
                    />
                </div>
                <input type="submit" className="btn btn-primary" value="Login" />
            </form>
            <p className="my-1">
                Don't have an account? <Link to="/register">Sign Up</Link>
            </p>
        </Fragment>
    )
}

export default Login