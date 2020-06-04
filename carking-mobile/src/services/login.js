import api from "./api";

export default async function login(login, password) {
  try {
    const response = await api().post("/login", {
      login,
      password
    });
    if (response.data.token) {
      localStorage.setItem("user", response.data.token);
      return true;
    }
    return false;
  } catch (err) {
    return false;
  }
}
