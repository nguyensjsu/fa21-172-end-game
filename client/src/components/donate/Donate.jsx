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

        this.handleUsernameChange = this.handleUsernameChange.bind(this)
        this.handlePasswordChange = this.handlePasswordChange.bind(this)
    }

    handleUsernameChange(e) {
        //console.log(e.target.value)
        this.setState({username: e.target.value})
    }

    handlePasswordChange(e) {
        //console.log(e.target.value)
        this.setState({password: e.target.value})
    }
    
    render() {
        return (
            <div>
                Username: <input type='text' name='username' value={this.state.username} onChange={this.handleUsernameChange}/>
                Password: <input type='password' name='password' value={this.state.password} onChange={this.handlePasswordChange}/>
                <button>Login</button>
            </div>
        )
    }
}

export default Donate