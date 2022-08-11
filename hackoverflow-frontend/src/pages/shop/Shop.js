import React from "react";
import { useState, useEffect } from "react";
import useAuth from "../../auth/useAuth";
import {
  Box,
  Flex,
  Heading,
  Text,
  Stack,
  Divider,
  Grid,
  GridItem,
  Button,
} from "@chakra-ui/react";

import { ShopReward, CartItem } from "../../components";
import { NavBar } from "../../container";

const Shop = () => {
  const [balance, setBalance] = useState(0);
  const [itemList, setItemList] = useState([]);
  const [cartItem, setCartItem] = useState([]);
  const { auth } = useAuth();

  let total = 0;
  total = cartItem
    .map((item) => item.cost * item.quantity)
    .reduce((a, b) => a + b, 0);

  const checkOutHandler = () => {
    console.log(cartItem);

    let checkOutItems = [];

    for (let i = 0; i < cartItem.length; i++) {
      checkOutItems.push({
        itemId: cartItem[i].itemId,
        quantity: cartItem[i].quantity,
      });
    }

    // cartItem.map((item) => {
    //   checkOutItems.push({
    //     rewardId: item.rewardId,
    //     quantity: item.quantity,
    //   });
    // });

    console.log(checkOutItems);
    fetch("https://hackoverflow-ms-gateway.herokuapp.com/shop/purchase/", {
      method: "POST",
      headers: {
        Authtoken: "f44638ea-c7e6-46cc-b070-94c3642e49e0",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        purchases: checkOutItems,
      }),
    });

    setBalance(balance - total);
    setCartItem([]);
  };

  useEffect(() => {
    fetch("https://hackoverflow-ms-gateway.herokuapp.com/reward/inquire/", {
      headers: { Authtoken:"f44638ea-c7e6-46cc-b070-94c3642e49e0" },
    })
      .then((response) => response.json())
      .then((data) => {
        setItemList(data.rewards);
      })
      .catch((err) => console.log(err));

    fetch("https://hackoverflow-ms-gateway.herokuapp.com/game-profile/", {
      headers: { Authtoken: "f44638ea-c7e6-46cc-b070-94c3642e49e0" },
    })
      .then((response) => response.json())
      .then((data) => {
        setBalance(data.credits);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <>
      <NavBar />
      <Flex
        position="relative"
        flexDirection="column"
        width="100%"
        height="100%"
        justifyContent="center"
      >
        <Flex justifyContent="space-between" mt="2%">
          <Heading ml="20px" mb="10px">
            Shop Exchange
          </Heading>
          <Heading mb="10px" pr="30px" size="md" pt="1%">
            Balance Gold: {balance}G
          </Heading>
        </Flex>

        <Divider />
        <Flex direction="row" justifyContent="space-between">
          <Box
            backgroundColor={"white"}
            borderRadius="5px"
            borderColor="gray.400"
            height={"100%"}
            width="60%"
            m="20px"
            boxShadow="lg"
          >
            {itemList.map((item) => (
              <ShopReward
                item={item}
                cartItem={cartItem}
                setCartItem={setCartItem}
                key={`${item.rewardId}`}
              />
            ))}
          </Box>
          <Box
            backgroundColor={"white"}
            borderRadius="5px"
            height="100%"
            width="40%"
            m="20px"
            alignItems="center"
            boxShadow="lg"
          >
            <Stack alignItems="center">
              <Heading size="lg" pt="10px" pb="10px">
                Cart
              </Heading>
            </Stack>

            {cartItem.length > 0 ? (
              cartItem.map((item, index) => (
                <CartItem
                  setCartItem={setCartItem}
                  cartItem={cartItem}
                  item={item}
                  index={index}
                  key={`${item.id}`}
                />
              ))
            ) : (
              <Stack alignItems="center">
                <Text fontSize="2xl" pt="20px" pb="20px">
                  No Items in Cart
                </Text>
              </Stack>
            )}

            <Divider />
            <Grid
              mt="10px"
              templateColumns="repeat(8,1fr)"
              templateRows="repeat(3,1fr)"
            >
              <GridItem colSpan={2} rowSpan={1} colStart={6}>
                <Text>Total Gold:</Text>
              </GridItem>
              <GridItem colSpan={1} rowSpan={1} colStart={8}>
                <Text>{total + " G"}</Text>
              </GridItem>
              {cartItem.length > 0 ? (
                <GridItem
                  colSpan={2}
                  rowSpan={2}
                  colStart={6}
                  pt="10px"
                  pb="20px"
                >
                  <Button onClick={checkOutHandler}>Check Out</Button>
                </GridItem>
              ) : (
                ""
              )}
            </Grid>
          </Box>
        </Flex>
      </Flex>
    </>
  );
};

export default Shop;
