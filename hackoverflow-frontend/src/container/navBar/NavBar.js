import React, { ReactNode } from "react";
import { useState } from "react";
import {
  Link as Chakralink,
  Box,
  Flex,
  Avatar,
  HStack,
  Text,
  IconButton,
  Button,
  Menu,
  MenuButton,
  MenuList,
  MenuItem,
  MenuDivider,
  useDisclosure,
  useColorModeValue,
  Stack,
  Progress,
  Heading,
} from "@chakra-ui/react";
import { HamburgerIcon, CloseIcon } from "@chakra-ui/icons";
import { Link } from "react-scroll";
import useAuth from "../../auth/useAuth";
import {useNavigate} from "react-router-dom";

const Links = ["Home", "Financial","Achievements","Shop"];

const navigate = useNavigate;


const NavLink = ({ children }: { children: ReactNode }) => (
  <Button variant="unstyled" color="white">
    <Link smooth spy to={children}>
      {children}
    </Link>
  </Button>
);

export default function NavBar() {
  const [currentExp, setCurrentExp] = useState(80);
  const [level, setLevel] = useState(10);
  const {setAuth} = useAuth();

  const handleSignOut = () => {
    setAuth(null);
    navigate("../", { replace: true });
  }

  return (
      <>
    <Flex
      bg={useColorModeValue("gray.100", "gray.900")}
      px={4}
      z={9}
      h={"65"}
      alignItems={"center"}
      justifyContent={"space-between"}
      position="fixed"
      width={"100%"}
      bgColor="#121212"
      zIndex={15}
    >
      <HStack spacing={8} alignItems={"center"}>
        <Heading color="white" mr="2rem" ml="2rem">
          FinanceStory
        </Heading>
        <HStack as={"nav"} spacing={12} display={{ base: "none", md: "flex" }}>
          {Links.map((link) => (
              <Chakralink href={link}>
                <Button variant="unstyled" color="white">
                  {link}
                </Button>
              </Chakralink>
          ))}
        </HStack>
      </HStack>
      <Flex color="white">
        <Text pt="13px" pr="10px">
          Level: {level}
        </Text>
        <Stack spacing={0} w="200px">
          <Text>Exp: {currentExp} / 100</Text>
          <Progress colorScheme="green" size="sm" value={currentExp} />
        </Stack>
      </Flex>

      <Flex alignItems={"center"}>
        <Menu>
          <MenuButton
            as={Button}
            rounded={"full"}
            variant={"link"}
            cursor={"pointer"}
            minW={0}
          >
            <Avatar
              size={"sm"}
              src={
                "https://en.meming.world/images/en/6/6e/Surprised_Pikachu.jpg"
              }
            />
          </MenuButton>
          <MenuList>
            <MenuItem>Edit Profile</MenuItem>
            <MenuItem>Settings</MenuItem>
            <MenuDivider />
            <MenuItem onClick={handleSignOut}>Sign out</MenuItem>
          </MenuList>
        </Menu>
      </Flex>
    </Flex>
  <Box h={"65"} />
  </>
  );
}
