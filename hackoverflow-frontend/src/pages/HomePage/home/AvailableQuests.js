import React from "react";
import { Grid, Flex, Box, chakra, Image, Button, Text, Stack } from "@chakra-ui/react";

const QuestList = [
  {
    title: "abc",
    details: "Lorem ipsum dolor sit!",
    imageURL: "https://en.meming.world/images/en/6/6e/Surprised_Pikachu.jpg",
  },
  {
    title: "abc",
    details: "Lorem ipsum dolor sit!",
    imageURL: "https://en.meming.world/images/en/6/6e/Surprised_Pikachu.jpg",
  },
  {
    title: "abc",
    details: "Lorem ipsum dolor sit!",
    imageURL: "https://en.meming.world/images/en/6/6e/Surprised_Pikachu.jpg",
  },
  {
    title: "abc",
    details: "Lorem ipsum dolor sit!",
    imageURL: "https://en.meming.world/images/en/6/6e/Surprised_Pikachu.jpg",
  },
  {
    title: "abc",
    details: "Lorem ipsum dolor sit!",
    imageURL: "https://en.meming.world/images/en/6/6e/Surprised_Pikachu.jpg",
  }
];

const Quest = ({ title, details, imageURL }) => {
  return (
      <Box
        maxW="xs"
        mx="auto"
        bg="white"
        _dark={{
          bg: "gray.800",
        }}
        shadow="lg"
        rounded="lg"
      >
        <Box px={4} py={2}>
          <chakra.h1
            color="gray.800"
            _dark={{
              color: "white",
            }}
            fontWeight="bold"
            fontSize="3xl"
            textTransform="uppercase"
          >
            {title}
          </chakra.h1>
          <chakra.p
            mt={1}
            fontSize="sm"
            color="gray.600"
            _dark={{
              color: "gray.400",
            }}
          >
            {details}
          </chakra.p>
        </Box>

        <Image h={48} w="" fit="cover" mt={2} src={imageURL} />
        <Stack>
          <Button
            bg="white"
            fontSize="xs"
            color="gray.900"
            fontWeight="bold"
            rounded="lg"
            textTransform="uppercase"
            _hover={{
              bg: "gray.200",
            }}
            _focus={{
              bg: "gray.400",
            }}
            onClick
          >
            Add Quest
          </Button>
        </Stack>
      </Box>

  );
};

export const AvailableQuests = () => {
  return (
    <Flex gap={"10px"} overflow="auto" height={"100%"}>
      {QuestList.map((item) => (
        <Quest
          title={item.title}
          details={item.details}
          imageURL={item.imageURL}
        />
      ))}
    </Flex>
  );
};
