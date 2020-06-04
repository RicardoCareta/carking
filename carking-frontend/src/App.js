import React from "react";
import "./styles.css";
import Routes from "./routes";

export default function App() {
  return (
    <div className="App">
      <link
        href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap"
        rel="stylesheet"
      />
      <div id="yellow-bg">
        <Routes />
      </div>
    </div>
  );
}
