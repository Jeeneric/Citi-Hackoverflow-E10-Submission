import React from "react";
import {
  CircularProgress,
  CircularProgressLabel,
  Box,
  VStack,
  Text,
  Flex,
  Heading,
} from "@chakra-ui/react";

const QuestArr = [
  {
    title: "Save $400",
    prog: 40,
  },
  {
    title: "Plan Finances",
    prog: 40,
  },
  {
    title: "Deposit $200",
    prog: 40,
  },
  {
    title: "Buy car",
    prog: 40,
  },
  {
    title: "Buy hdb",
    prog: 10,
  },
  {
    title: "School Debt",
    prog: 70,
  },
];

const Quest = ({ title, prog }) => {
  return (
    <Box
      p={8}
      maxW={"350px"}
      w={"full"}
      maxH={"330px"}
      h={"full"}
      boxShadow={"lg"}
      rounded={"lg"}
      pos={"static"}
      zIndex={0}
    >
      <VStack>
        <Heading size='sm'>{title}</Heading>
        <CircularProgress value={prog} color="green.400" size="150px">
          <CircularProgressLabel>{prog}%</CircularProgressLabel>
        </CircularProgress>
      </VStack>
    </Box>
  );
};

export const CurrentQuests = () => {
  return (
      <Flex width= "100%" gap={"10px"} overflow="auto">
        {QuestArr.map((item) => (
          <Quest title={item.title} prog={item.prog} />
        ))}
      </Flex>
  );
};
