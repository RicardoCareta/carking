import React from "react";

import "./style.css";

function Modal({ message, open, confirmCallBack, cancelCallBack }) {
  return (
    <div id="modal" class={"modal " + (open ? "open" : "")}>
      <div class="modal-content">
        <div class="modal-body">
          <div id="welcome">
            <h2 id="subtitulo">{message}</h2>
          </div>

          <div id="footer-button">
            <div id="button-yes">
              <input
                type="button"
                draggable="false"
                id="btnYes"
                value="Sim"
                onClick={confirmCallBack}
              />
            </div>

            <div id="button-cancel">
              <input
                type="button"
                draggable="false"
                id="btnCancel"
                value="Cancelar"
                onClick={cancelCallBack}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Modal;
