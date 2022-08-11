import React, { useEffect, useRef, useState } from "react";
import useAuth from "../../auth/useAuth";
import {
  Input,
  chakra,
  Flex,
  Stack,
  Avatar,
  Heading,
  Box,
  InputLeftElement,
  InputRightElement,
  Button,
  FormHelperText,
  FormControl,
  InputGroup,
  Link,
} from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";
import { FaUserAlt, FaLock } from "react-icons/fa";

const CFaUserAlt = chakra(FaUserAlt);
const CFaLock = chakra(FaLock);

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);
  const { setAuth } = useAuth();
  // const usernameRef = useRef();
  const errRef = useRef();

  const [username, setUser] = useState("");
  const [password, setPwd] = useState("");
  const [errMsg, setErrMsg] = useState("");
  let navigate = useNavigate();

  useEffect(() => {
    setErrMsg("");
  }, [username, password]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await fetch("https://hackoverflow-ms-gateway.herokuapp.com/login", {
        method: "post",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Expose-Headers": "Authtoken",
          // "Access-Control-Allow-Credentials": true,
          Accept: "application/json",
        },
        body: JSON.stringify({
          username,
          password,
        }),
      }).then((response) => {
        if (response.status === 200) {
          console.log(response.headers.get("Authtoken"));
          const accessToken = response.headers.get("Authtoken");
          setAuth({accessToken});
          setUser("");
          setPwd("");
          navigate("home", { replace: true });
        }
      });
    } catch (err) {
      // edit accordingly to err received
      if (!err?.response) {
        setErrMsg("No Server Response");
      } else if (err.response?.status === 401) {
        setErrMsg("Unauthorised");
      } else {
        setErrMsg("Login Failed");
      }
    }
  };

  const handleShowClick = () => setShowPassword(!showPassword);

  return (
    <Flex
      flexDirection="column"
      width="100wh"
      height="100vh"
      backgroundColor="gray.200"
      justifyContent="center"
      alignItems="center"
    >
      <Stack
        flexDir="column"
        mb="2"
        justifyContent="center"
        alignItems="center"
      >
        <Avatar bg="black" />
        <Heading color="black">Welcome</Heading>
        <p ref={errRef}>{errMsg}</p>
        <Box minW={{ base: "90%", md: "468px" }}>
          <form onSubmit={handleSubmit}>
            <Stack
              spacing={4}
              backgroundColor={"whiteAlpha.900"}
              boxShadow="md"
              p="1rem"
            >
              <FormControl>
                <InputGroup bgColor={"gray.100"}>
                  <InputLeftElement
                    pointerEvents="none"
                    children={<CFaUserAlt color="gray.500" />}
                  />
                  <Input
                    type="text"
                    placeholder="username"
                    id="username"
                    color="black"
                    autoComplete="off"
                    onChange={(e) => setUser(e.target.value)}
                    value={username}
                    required
                  />
                </InputGroup>
              </FormControl>
              <FormControl>
                <InputGroup>
                  <InputLeftElement
                    pointerEvents="none"
                    children={<CFaLock color="gray.500" />}
                  />
                  <Input
                    type={showPassword ? "text" : "password"}
                    placeholder="Password"
                    color="teal.500"
                    onChange={(e) => setPwd(e.target.value)}
                    value={password}
                  />
                  <InputRightElement width="4.5rem">
                    <Button
                      h="1.75rem"
                      size="sm"
                      onClick={handleShowClick}
                      color="black"
                    >
                      {showPassword ? "Hide" : "Show"}
                    </Button>
                  </InputRightElement>
                </InputGroup>
                <FormHelperText textAlign="right">
                  <Link>forgot password?</Link>
                </FormHelperText>
              </FormControl>

              <Button
                borderRadius={0}
                type="submit"
                variant="solid"
                colorScheme="teal"
                width="full"
              >
                Login
              </Button>
            </Stack>
          </form>
        </Box>
      </Stack>
      <Box>
        New to us?{" "}
        <Link color="teal.500" href={"register"}>
          Sign Up
        </Link>
      </Box>
    </Flex>
  );
};

export default Login;
