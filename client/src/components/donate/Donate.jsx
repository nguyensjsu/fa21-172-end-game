import React, { Component } from 'react';

class Donate extends Component 
{
    render() 
    {
        return (
            <div className='Donate'>
                <LoginComponent/>
            </div>
        )
    }
}

class LoginComponent extends Component 
{
    constructor(props) 
    {
        super(props)

        this.state = {
            username: '',
            password: '',
            loginFailed: false,
            loginSuccess: false
        }
        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
    }

    handleChange(e) 
    {
        //console.log(this.state)
        this.setState({[e.target.name]: e.target.value})
    }
    
    loginClicked() 
    {
        // test, testpass
        if(this.state.username === 'test' && this.state.password === 'testpass')
        {
            this.setState({loginSuccess: true})
            this.setState({loginFailed: false})
        }
        else
        {
            this.setState({loginSuccess: false})
            this.setState({loginFailed: true})
        }
        console.log(this.state)
    }

    render() 
    {
        return (
            <div>
                <AlertLoginFailed loginFailed={this.state.loginFailed}/>
                <AlertLoginSuccess loginSuccess={this.state.loginSuccess}/>
                Username: <input type='text' name='username' value={this.state.username} onChange={this.handleChange}/>
                Password: <input type='password' name='password' value={this.state.password} onChange={this.handleChange}/>
                <button onClick={this.loginClicked}>Login</button>
            </div>
        )
    }
}

function AlertLoginFailed(props) 
{
    if (props.loginFailed)
    {
        return <div>Invalid Credentials</div>
    }
    return null
}

function AlertLoginSuccess(props) 
{
    if (props.loginSuccess)
    {
        return <div>Login Successful</div>
    }
    return null
}

export default Donate