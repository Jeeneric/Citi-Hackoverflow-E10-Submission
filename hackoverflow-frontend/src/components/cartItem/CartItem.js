import React from "react";
import { useState, useEffect } from "react";
import {
  Flex,
  Text,
  Box,
  Grid,
  Stack,
  GridItem,
  Heading,
  Divider,
  Button,
} from "@chakra-ui/react";

const CartItem = ({ item, index, cartItem, setCartItem }) => {
  const [del, setDel] = useState(false);

  useEffect(() => {
    if (del) {
      if (cartItem.map((item, index) => item.id).includes(item.id)) {
        const newCart = [...cartItem];
        newCart.splice(index, 1);
        setCartItem(newCart);
      }
      setDel(false);
    }
  }, [del]);

  // useEffect(() => {
  //   console.log("cartID", item.rewardId);
  //   console.log("cart", cartItem);
  // }, [cartItem]);

  return (
    <Box>
      <Stack direction="column">
        <Grid
          p="10px"
          templateColumns="repeat(10, 1fr)"
          templateRows="repeat(3, 1fr)"
        >
          <GridItem colStart={1} rowStart={1} colSpan={1}>
            <Text>{index + 1 + "."}</Text>
          </GridItem>
          <GridItem colStart={2} colSpan={5}>
            <Heading size="sm">{item.name}</Heading>
          </GridItem>
          <GridItem colStart={8} colSpan={1} rowSpan={1}>
            <Text as="u">Qty</Text>
          </GridItem>
          <GridItem colStart={8} rowStart={2} colSpan={1} rowSpan={1}>
            <Text>{item.quantity}</Text>
          </GridItem>
          <GridItem colStart={9} colSpan={1} rowSpan={1}>
            <Text as="u">Price</Text>
          </GridItem>
          <GridItem colStart={9} rowStart={2} colSpan={1} rowSpan={1}>
            <Text>{item.cost * item.quantity + "G"}</Text>
          </GridItem>
          <GridItem colStart={2} rowStart={2} colSpan={5} rowSpan={2}>
            <Text>{item.description}</Text>
          </GridItem>
          <GridItem colStart={10} rowStart={2} colSpan={1} pl={"15px"}>
            <Button onClick={() => setDel(true)} size="xs" colorScheme="red">
              x
            </Button>
          </GridItem>
        </Grid>
      </Stack>
      <Divider />
    </Box>
  );
};

export default CartItem;
