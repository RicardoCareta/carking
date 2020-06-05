import api from "./api";

export default async function login(login, password) {
  try {
    const response = await api().post("/login", {
      login,
      password,
      access: "web"
    });
    if (response.data.token) {
      localStorage.setItem("user", response.data.token);
      return {
        access: true,
        role: response.data.role
      };
    }
    return false;
  } catch (err) {
    if (err.response && err.response.status === 418) {
      return -1;
    }
    console.log(err);
    return false;
  }
}
