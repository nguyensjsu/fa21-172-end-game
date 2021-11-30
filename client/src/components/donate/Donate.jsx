import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
class Donate extends Component 
{
    render() 
    {
        return (
            <div className='Donate'>
                <Router>
                    <Routes>
                        <Route path='/' element={<LoginComponent />}/>
                        <Route path='/login' element={<LoginComponent />}/>
                        <Route path='/welcome/*' element={<WelcomeComponent />}/>
                        <Route path='*' element={ <ErrorComponent /> } />
                    </Routes>
                </Router>
            </div>
        )
    }
}

class WelcomeComponent extends Component 
{
    render()
    {
        return <div>Welcome!</div>
    }
}

function ErrorComponent() 
{
    return <div>Error! Invalid page.</div>
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
            //this.setState({loginSuccess: true})
            //this.setState({loginFailed: false})
            this.props.history.push('/welcome')
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
                {this.state.loginFailed && <div>Invalid Credentials</div>}
                {this.state.loginSuccess && <div>Login Successful</div>}
                Username: <input type='text' name='username' value={this.state.username} onChange={this.handleChange}/>
                Password: <input type='password' name='password' value={this.state.password} onChange={this.handleChange}/>
                <button onClick={this.loginClicked}>Login</button>
            </div>
        )
    }
}

export default Donate