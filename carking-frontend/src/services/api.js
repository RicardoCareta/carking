import axios from "axios";
import https from "https";

const getToken = () => {
  return localStorage.getItem("user");
};

const createAPI = () => {
  const api = axios.create({
    baseURL: "https://192.168.15.11:8080",
    headers: {
      Authorization: getToken()
    },
    httpAgent: new https.Agent({
      rejectUnauthorized: false
    })
  });
  return api;
};

export default createAPI;
