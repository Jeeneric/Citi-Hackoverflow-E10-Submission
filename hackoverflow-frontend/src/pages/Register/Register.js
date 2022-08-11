import React from "react";
import {
  Flex,
  Stack,
  Box,
  Heading,
  FormControl,
  FormLabel,
  Input,
  Button, InputGroup, InputRightElement,
} from "@chakra-ui/react";
import { useState, useRef, useEffect } from "react";

const USER_REGEX= /[a-zA-Z][a-zA-Z0-9-_]{3,23}$/;
const PWD_REGEX= /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,24}$/;

const Register = () => {

     const errRef = useRef();

     const [showPassword, setShowPassword] = useState(false);
     const [email, setEmail] = useState("");
     const [firstName, setFirstName] = useState("");
     const [lastName, setLastName] = useState("");

     const [userName, setUserName] = useState("");
     const [validUsername, setValidUsername] = useState(false);

     const [password, setPassword] = useState("");
     const [validPassword, setValidPassword] = useState(false);

     const [errMsg, setErrMsg] = useState('');
     const [success, setSuccess] = useState(false);

     useEffect(() => {
         setValidUsername(USER_REGEX.test(userName));
     }, [userName])

     useEffect(() => {
         setValidPassword(PWD_REGEX.test(password));
     }, [password])

     useEffect(() => {
         setErrMsg('');
     }, [userName, password])

     const handleShowClick = () => setShowPassword(!showPassword);

     const handleEmailChange = (e) => {
         setEmail(e.target.value);
     };

     const handleFirstNameChange = (e) => {
         setFirstName(e.target.value);
     };

     const handleLastNameChange = (e) => {
         setLastName(e.target.value);
     };

     const handlePasswordChange = (e) => {
         setPassword(e.target.value);
     };
     const handleUsernameChange = (e) => {
         setUserName(e.target.value);
     };

     const handleSubmit = async (e) => {
         e.preventDefault();
         // submit data
         const v1 = USER_REGEX.test(userName);
         const v2 = PWD_REGEX.test(password);
         if (!v1 || !v2) {
             setErrMsg("Invalid Entry");
             return;
         }
         const details = {
             firstName, lastName, email,
             userName, password
         };
             try{
             fetch("https://hackoverflow-ms-gateway.herokuapp.com/register",{
                 method:'POST',
                 mode: 'no-cors',
                 headers:{
                     "Access-Control-Allow-Origin": "*",
                     "Access-Control-Allow-Credentials": true
                 },
                 body: JSON.stringify(details)
             }
                 ).then(
                 (response) => {
                     console.log(response);
                     if (response.status === 200) {
                         console.log(response.status)
                         setSuccess(true);
                         setUserName('');
                         setPassword('');
                     }
                 })
         } catch (err) {
                 if (!err?.response) {
                     setErrMsg('No Server Response');
                 } else if (err.response?.status === 409) {
                     setErrMsg('Username Taken');
                 } else {
                     setErrMsg('Registration Failed')
                 }
             }

     }

         return (
             <> {success ? (
                 <section>
                     <h1>Success!</h1>
                     <p>
                         <a href="../">Sign In</a>
                     </p>
                 </section>
             ) : (
             <Flex
                 flexDirection="column"
                 width="100wh"
                 height="100vh"
                 backgroundColor="gray.200"
                 justifyContent="center"
                 alignItems="center"
             >
                 <Stack flexDir="column" justifyContent="center" alignItems="center">
                     <Heading color="black">Create your Account</Heading>
                     <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                     <Box minW={{base: "90%", md: "468px"}}>
                         <form onSubmit={handleSubmit}>
                             <Stack
                                 spacing={4}
                                 backgroundColor={"whiteAlpha.900"}
                                 boxShadow="md"
                                 p="1rem"
                                 borderRadius="5px"
                             >
                                 <Stack flexDir="row" spacing="0">
                                     <FormControl isRequired>
                                         <FormLabel>First Name</FormLabel>
                                         <Input
                                             value={firstName}
                                             borderColor="black"
                                             type="text"
                                             placeholder="First Name"
                                             onChange={handleFirstNameChange}
                                         />
                                     </FormControl>
                                     <FormControl>
                                         <FormLabel>Last Name</FormLabel>
                                         <Input
                                             value={lastName}
                                             borderColor="black"
                                             type="text"
                                             placeholder="Last Name"
                                             onChange={handleLastNameChange}
                                         />
                                     </FormControl>
                                 </Stack>

                                 <FormControl isRequired>
                                     <FormLabel>Email Address</FormLabel>
                                     <Input
                                         value={email}
                                         borderColor="black"
                                         type="email"
                                         placeholder="Email Address"
                                         onChange={handleEmailChange}
                                     />
                                 </FormControl>
                                 <FormControl isRequired>
                                     <FormLabel>Username</FormLabel>
                                     <Input
                                         value={userName}
                                         borderColor="black"
                                         type="text"
                                         placeholder="Username"
                                         onChange={handleUsernameChange}
                                         autoComplete="off"
                                     />
                                 </FormControl>
                                 <FormControl isRequired>
                                     <FormLabel>Password</FormLabel>
                                     <InputGroup>
                                         <Input
                                             type={showPassword ? "text" : "password"}
                                             placeholder="Password"
                                             borderColor="black"
                                             onChange={handlePasswordChange} value={password}
                                         />
                                         <InputRightElement width="4.5rem">
                                             <Button h="1.75rem" size="sm" onClick={handleShowClick} color="black">
                                                 {showPassword ? "Hide" : "Show"}
                                             </Button>
                                         </InputRightElement>
                                     </InputGroup>
                                 </FormControl>

                                 <Button
                                     borderRadius={0}
                                     type="submit"
                                     variant="solid"
                                     colorScheme="teal"
                                     width="full"
                                 >
                                     Submit
                                 </Button>
                             </Stack>
                         </form>
                     </Box>
                 </Stack>
             </Flex>)}
             </>
         )

 }

export default Register;