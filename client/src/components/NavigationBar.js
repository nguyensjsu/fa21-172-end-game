import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';

import { Navbar, Container, Nav } from 'react-bootstrap';

class NavigationBar extends React.Component
{
    render()
    {
        return (
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="/">
                    <img src="https://media.istockphoto.com/vectors/human-palm-heart-inside-logo-design-vector-donate-symbol-illustration-vector-id1162212464?k=20&m=1162212464&s=612x612&w=0&h=mU4ne9paMyh3ugrF1S7hJpJw2TMTNB2B_79HS8NnMUY=" width="55" height="55" alt="brand"/>
                    D0N8
                </Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="/">Login</Nav.Link>
                    <Nav.Link href="/">Sign Up</Nav.Link>
                </Nav>
            </Navbar>
        )
    }
}

export default NavigationBar;