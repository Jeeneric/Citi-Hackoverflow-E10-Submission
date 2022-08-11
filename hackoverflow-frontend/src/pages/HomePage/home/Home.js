import React from "react";
import {
  Box,
  HStack,
  VStack,
  Stack,
  StackDivider,
  Heading,
  Flex,
  Divider,
} from "@chakra-ui/react";
import { Bulletin } from "./Bulletin";
import { CurrentQuests } from "./CurrentQuests";
import { AvailableQuests } from "./AvailableQuests";
import { NavBar } from "../../../container";
import useAuth from "../../../auth/useAuth";

const Home = () => {
  const { auth } = useAuth();
  return (
    <>
      <NavBar />

      <Box height="90%" width="100%">
        <HStack
          divider={<StackDivider borderColor="gray.200" />}
          spacing={4}
          align="stretch"
        >
          <Box width="50%">
            <VStack spacing={4} align="stretch" borderColor="gray.400">
              <Stack pl="2rem" pt="1rem" alignItems="center">
                <Heading fontSize={"4xl"}>Welcome User!</Heading>
              </Stack>
              <Divider />

              <Stack alignItems="center">
                <Heading>Ongoing Quest</Heading>
              </Stack>
              <CurrentQuests />
              <Divider />
              <Stack alignItems="center" pt="1rem">
                <Heading>Available Quests</Heading>
              </Stack>
              <AvailableQuests />
            </VStack>
          </Box>
          <Flex width="100%" bg="gray.600">
            <Bulletin />
          </Flex>
        </HStack>
      </Box>
    </>
  );
};

export default Home;
