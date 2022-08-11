import React, { useState, onAdd } from "react";

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

const TransferForm = ({ onAdd }) => {
  const [beneAccountId, setbeneAccountId] = useState("");
  const [recvAccountId, setrecvAccountId] = useState("");
  const [transferAmount, settransferAmount] = useState(0);

  const onSubmit = (e) => {
    e.preventDefault();
    if (!beneAccountId || !recvAccountId || !transferAmount) {
      alert("Incomplete form");
      return;
    }
    onAdd({ beneAccountId, recvAccountId, transferAmount });
    setbeneAccountId("");
    setrecvAccountId("");
    settransferAmount(0);
  };
  return (
    <Flex>
      <Box bgColor="gray.200" w="400px" height="410px" borderRadius="10px">
        <Stack alignItems="center" mt="1rem">
          <Heading size="lg">Account Transfer</Heading>
        </Stack>

        <Stack pl="1rem" pr="1rem" alignItems="center" stack="column" pt="2rem">
          <FormControl>
            <FormLabel>From Account:</FormLabel>
            <Input
              bg="white"
              type="text"
              onChange={(e) => setbeneAccountId(e.target.value)}
            />
          </FormControl>
          <FormControl>
            <FormLabel>To Account:</FormLabel>
            <Input
              bg="white"
              type="text"
              onChange={(e) => setrecvAccountId(e.target.value)}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Amount:</FormLabel>
            <Input
              bg="white"
              type="number"
              onChange={(e) => settransferAmount(e.target.value)}
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

export default TransferForm;
