// export default (doesn't need {}), { export } <- needs {}
import React, { Component } from 'react';

import Donate from './components/donate/Donate';

import './App.css';
 
class App extends Component {
  render() {
    return (
      <div className="App">
        <Donate/>
      </div>
    );
  }
}


export default App;