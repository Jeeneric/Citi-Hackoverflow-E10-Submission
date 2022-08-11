import React from "react";

import { Routes, Route } from "react-router-dom";
import {
  Home,
  FinancialView,
  Achievements,
  Shop,
  Login,
  Layout,
  Register,
} from "./pages";
import RequireAuth from "./auth/requireAuth";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        {/* public routes */}
        <Route path="" element={<Login />} />
        <Route path="register" element={<Register />} />
        {/* protected routes */}
        {/* <Route element={<RequireAuth />}> */}
        <Route path={"home"} element={<Home />} />
        <Route path={"shop"} element={<Shop />} />
        <Route path={"achievement"} element={<Achievements />} />
        <Route path={"financial"} element={<FinancialView />} />
        {/* </Route> */}
      </Route>
    </Routes>
  );
};

export default App;
