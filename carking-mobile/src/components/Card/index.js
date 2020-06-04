import React from "react";

import "./style.css";

export default function Card({
  isEntrance,
  id,
  model,
  brand,
  plate,
  color,
  name,
  callBackClick
}) {
  return (
    <div id="card" onClick={callBackClick}>
      <div id="type-card">
        <svg
          className={isEntrance ? "entrance" : "takeoff"}
          width="75"
          height="auto"
          viewBox="0 0 982 807"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path d="M125.432 622.562C97.7719 622.562 73.5697 607.482 66.2456 585.558C54.6866 551.076 41 491.711 41 404C41 316.289 54.6866 256.924 66.2456 222.442C73.5697 200.518 97.7719 185.438 125.432 185.438H821.526C870.115 185.438 911.829 213.447 921.752 252.536C931.613 291.457 941.167 344.484 941.167 404C941.167 463.516 931.613 516.543 921.731 555.464C911.809 594.553 870.115 622.562 821.526 622.562H125.432Z" />
          <path
            d="M572.917 488.062L675.208 555.312C675.208 555.312 695.667 488.819 695.667 404C695.667 319.181 675.208 252.688 675.208 252.688L572.917 319.938V488.062ZM245.583 319.938L163.75 252.688C163.75 252.688 143.292 304.167 143.292 403.933C143.292 503.698 163.75 555.312 163.75 555.312L245.583 488.062V319.938ZM511.542 538.5H298.12L236.745 588.938H584.128L511.542 538.5ZM511.542 269.5H298.12L236.745 219.062H584.128L511.542 269.5Z"
            fill="#410121"
          />
          <path d="M593.375 135H634.292V202.25H593.375V135ZM593.375 605.75H634.292V673H593.375V605.75Z" />
        </svg>
      </div>

      <div id="card-infos">
        <p class="ticket-infos ticket-title">
          {brand} - {model}
        </p>
        <hr class="line" />
        <p class="ticket-infos">
          {plate} - {color}
        </p>
        <p class="ticket-infos">
          <span class="vality">Dono:</span> {name && name.split(" ")[0]}
        </p>
      </div>
    </div>
  );
}
