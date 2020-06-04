import axios from "axios";

const getToken = () => {
  return localStorage.getItem("user");
};

const createAPI = () => {
  const api = axios.create({
    baseURL: "https://192.168.15.11:8080",
    headers: {
      Authorization: getToken()
    }
  });
  return api;
};

export default createAPI;
