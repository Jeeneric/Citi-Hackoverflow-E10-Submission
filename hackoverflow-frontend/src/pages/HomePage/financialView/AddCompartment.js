import React, { useState, onAdd, useEffect } from "react";
import {
  Flex,
  Box,
  Stack,
  FormControl,
  Input,
  FormLabel,
  Button,
  Heading,
} from "@chakra-ui/react";
const AddCompartment = ({ onAdd }) => {
  const [name, setname] = useState("");
  const [goal, setgoal] = useState(0);
  const [description, setdescription] = useState("");
  const [category, setcategory] = useState("CUSTOM");
  const [currAmount, setCurrAmount] = useState(0);

  const onSubmit = (e) => {
    e.preventDefault();
    if (!name || !goal) {
      alert("Please add name and/or goal");
      return;
    }
    // console.log(name, goal)
    onAdd({ name, goal, description, category });
    setname("");
    setdescription("");
    setgoal(0);
  };
  return (
    <Flex>
      <Box bgColor="gray.200" w="400px" height="410px" borderRadius="10px">
      <Stack alignItems="center" mt="1rem">
          <Heading size="lg">Set Destination</Heading>
        </Stack>
        <Stack pl="1rem" pr="1rem" alignItems="center" stack="column" pt="2rem">
          <FormControl>
            <FormLabel>Input Name:</FormLabel>
            <Input
              bg="white"
              type="text"
              onChange={(e) => setname(e.target.value)}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Description</FormLabel>
            <Input
              bg="white"
              type="text"
              onChange={(e) => setdescription(e.target.value)}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Goal Amount</FormLabel>
            <Input
              bg="white"
              type="text"
              onChange={(e) => setgoal(e.target.value)}
            />
          </FormControl>
        </Stack>
        <Stack alignItems="center" mt="2rem">
          <Button bg="black" color="white" size="lg" onClick={onSubmit}>
            Submit
          </Button>
        </Stack>
      </Box>
    </Flex>
  );
};

export default AddCompartment;
