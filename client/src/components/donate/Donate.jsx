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
    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: ''
        }
        this.handleChange = this.handleChange.bind(this)
    }

    handleChange(e) {
        //console.log(this.state)
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    
    render() {
        return (
            <div>
                Username: <input type='text' name='username' value={this.state.username} onChange={this.handleChange}/>
                Password: <input type='password' name='password' value={this.state.password} onChange={this.handleChange}/>
                <button>Login</button>
            </div>
        )
    }
}

export default Donate