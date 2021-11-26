import React, { Component } from 'react';

class Donate extends Component {
    render() {
        return (
            <div className='Donate'>
                <LoginComponent/>
            </div>
        )
    }
}

class LoginComponent extends Component {
    render() {
        return (
            <div>
                Username: <input type='text' name='username'/>
                Password: <input type='password' name='password'/>
                <button>Login</button>
            </div>
        )
    }
}

export default Donate