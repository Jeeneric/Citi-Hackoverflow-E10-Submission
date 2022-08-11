import { useLocation, Outlet, useNavigate } from "react-router-dom";
import useAuth from "./useAuth";

const RequireAuth = () => {
  let Navigate = useNavigate();
  const { auth } = useAuth();
  const location = useLocation();

  console.log(auth.accessToken);

  return auth?.accessToken != null ? (
    // auth?.accessToken?.find(token => AcceptedTokens?.includes(token))
    // add code to check authentication
    <Outlet />
  ) : (
    <Navigate to="../" state={{ from: location }} replace />
  );
};

export default RequireAuth;
