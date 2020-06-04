import React, { useState, useEffect } from "react";

import Car from "../car";

import "./style.css";
import getParkingMapped from "../../services/getParkingMapped";

function ParkingMapPanel({ callBackParkingMapSelect, isOut = false }) {
  const [cars, setCars] = useState([]);

  const loadCars = async () => {
    const result = await getParkingMapped();
    setCars(result);
  };

  useEffect(() => {
    loadCars();
  }, []);

  const callbackCarClick = (car, columnIndex, rowIndex) => {
    let tempCars = [...cars];
    let item = { ...tempCars[rowIndex][columnIndex] };

    if (
      (item.status !== "busy" && !isOut) ||
      (isOut && item.status === "busy")
    ) {
      const vaga = rowIndex + 1 + String.fromCharCode(65 + columnIndex);
      const result = window.confirm("Deseja escolher a vaga " + vaga);
      if (!result) {
        return;
      }
      for (let row = 0; row < tempCars.length; row++) {
        for (let column = 0; column < tempCars[row].length; column++) {
          tempCars[row][column].selected = false;
        }
      }

      item.selected = true;
      tempCars[rowIndex][columnIndex] = item;
      setCars(tempCars);
      if (callBackParkingMapSelect instanceof Function) {
        callBackParkingMapSelect(vaga, item.id);
      }
    }
  };

  return (
    <div>
      <div id="parking" className={isOut ? "parking-out" : ""}>
        {cars.map((car, idxCar) => (
          <div className="row-parking">
            {car.map((currentCar, idx) => (
              <Car
                isSelected={currentCar.selected}
                isParking={currentCar.status}
                callBackClick={() => {
                  callbackCarClick(currentCar, idx, idxCar);
                }}
              />
            ))}
          </div>
        ))}
      </div>
    </div>
  );
}
export default ParkingMapPanel;
