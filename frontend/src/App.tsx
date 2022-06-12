import React from 'react';
import { Switch, Route } from 'react-router-dom';
import { Home } from './pages/Home';
import { Auth } from './pages/Auth';

import "./App.scss";

function App() {
  return (
    <div className='app'>
      <Switch>
        <Route exact path='/' component={Home} />
        <Route path='/login' component={Auth} />
      </Switch>
    </div>
  );
}

export default App;
