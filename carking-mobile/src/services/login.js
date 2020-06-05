import api from "./api";

export default async function login(login, password) {
  try {
    const response = await api().post("/login", {
      login,
      password,
      access: "mobile"
    });
    if (response.data.token) {
      localStorage.setItem("user", response.data.token);
      return true;
    }
    return false;
  } catch (err) {
    if (err.response.status === 418) {
      return -1;
    }
    return false;
  }
}
