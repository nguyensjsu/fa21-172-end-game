import React from 'react';

class Welcome extends React.Component
{
    render()
    {
        return (
            <div className="container-fluid bg-dark text-light p-5">
            <div className="container bg-dark p-5">
              <h1 className="display-4">Welcome to D0N8</h1>
              <p>Donate to your favorite charities!</p>
              <a href="/" className="btn btn-primary">link</a>
            </div>
          </div>
        )
    }
}

export default Welcome;