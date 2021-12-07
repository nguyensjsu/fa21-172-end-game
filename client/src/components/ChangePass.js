import React, { Fragment, useState } from 'react'
import { Link } from 'react-router-dom';
import axios from 'axios';

const Register = () => {
    const [formData, setFormData] = useState({
        userName: '',
        password: ''
    }); 
    
    const { userName, password } = formData;

    const onChange = e => setFormData({ ...formData, [e.target.name]: e.target.value });

    const onSubmit = async e => {
        e.preventDefault();

        const newUser = {
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

            const body = JSON.stringify(newUser);

            const res = await axios.put('http://localhost:8080/users/changepass', body, config);
        }
        catch(err)
        {
            console.error(err.response.data);
        }
    }

    return (
        <Fragment>
            <h1 className="large text-primary">Change Password</h1>
            <p className="lead"><i className="fas fa-user"></i> Pick a new password</p>
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
                        placeholder="New Desired Password"
                        name="password"
                        value={password}
                        onChange={e => onChange(e)}
                        required
                    />
                </div>
                <input type="submit" className="btn btn-primary" value="ChangePassword" />
            </form>
        </Fragment>
    )
}

export default Register