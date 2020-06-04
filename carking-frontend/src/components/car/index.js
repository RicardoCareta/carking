import React from "react";

import "./style.css";

function Car({ position = 0, isParking = "empty", callBackClick, isSelected }) {
  return (
    <svg
      onClick={callBackClick}
      className={"car-svg " + (isSelected ? "selected" : isParking)}
      style={{
        position: "relative",
        top: "4vh"
      }}
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 48 48"
    >
      <path d="M7.127,38c-1.352,0-2.535-0.897-2.893-2.201C3.669,33.748,3,30.217,3,25s0.669-8.748,1.234-10.799C4.592,12.897,5.775,12,7.127,12h34.025c2.375,0,4.414,1.666,4.899,3.991C46.533,18.306,47,21.46,47,25s-0.467,6.694-0.95,9.009C45.565,36.334,43.527,38,41.152,38H7.127z" />
      <path
        fill="#410121"
        d="M29 30l5 4 0 0c0 0 1-3.955 1-9s-1-9-1-9l0 0-5 4V30zM13 20l-4-4c0 0-1 3.062-1 8.996S9 34 9 34l4-4V20zM26 33L15.568 33 12.568 36 29.548 36zM26 17L15.568 17 12.568 14 29.548 14z"
      />
      <path d="M30 9H32V13H30zM30 37H32V41H30z" />
    </svg>
  );
}

export default Car;
