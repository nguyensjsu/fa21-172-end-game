import React from 'react';
import './App.css';
import { Container, Row, Col } from 'react-bootstrap'
import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Charity from './components/Charity';
import Catalog from './components/Catalog'; 

function App() 
{
  const marginTop = {
    marginTop:"20px"
  };

  return (
    <div className="App">
      <NavigationBar/>
      <Container>
        <Row>
            <Col lg={12} style={marginTop}>
              <Welcome/>
              <Charity/>
              <Catalog/>
            </Col>
        </Row>
      </Container>
    </div>
  );
}

export default App;