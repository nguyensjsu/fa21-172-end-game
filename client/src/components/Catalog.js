import React from 'react'
import Charities from "../data/Charities";
import { useNavigate } from "react-router-dom";
import './Catalog.css';

const Catalog = () => {
    let navigate = useNavigate();

    const listItems = Charities.map((item) =>
        <div className="card" key={item.id}>
            <div className="card_img">
                <img src={item.img} />
            </div>

            <div className="card_header">
                <h2>{item.name}</h2>
                <p>{item.description}</p>
                <p className="price">{item.price}</p>
                <p className="price2">{item.price2}</p>
                <p className="price3">{item.price3}</p>
                <p className="price4">{item.price4}</p>
                <div className="btn" onClick={navigate()}>Donate!</div>
            </div>
        </div>
    );
    return (
        <div className="main_content">
            <h3>Charities</h3>
            {listItems}
        </div>
    )
}
export default Catalog;