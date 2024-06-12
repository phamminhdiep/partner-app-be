import React from 'react';
import { Link } from 'react-router-dom';
import Navbar from './Navbar';
import '../css/SuccessPage.css';

function SuccessPage() {
  return (
    <div>
        <Navbar></Navbar>
        <div className="card">
            <div style={{ borderRadius: '200px', height: '200px', width: '200px', background: '#F8FAF5', margin: '0 auto' }}>
            <i className="checkmark card-i">✓</i>
            </div>
            <h1 className='card-h1'>Success</h1>
            <p className='card-p'>We received your purchase request;<br /> we'll be in touch shortly!</p>
        </div>
    </div>
  );
}

export default SuccessPage;
