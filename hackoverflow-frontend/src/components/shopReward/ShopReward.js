import React from "react";
import { useState, useEffect } from "react";
import {
  Box,
  Flex,
  Text,
  Heading,
  Stack,
  Divider,
  Grid,
  GridItem,
  Image,
  IconButton,
  NumberInput,
  NumberInputField,
  NumberInputStepper,
  NumberIncrementStepper,
  NumberDecrementStepper,
} from "@chakra-ui/react";
import { AddIcon } from "@chakra-ui/icons";

const ShopReward = ({ item, cartItem, setCartItem }) => {
  const [quantity, setQuantity] = useState(0);
  const [clicked, setClicked] = useState(false);

  useEffect(() => {
    if (clicked && quantity > 0) {
      const newItem = { ...item, quantity };

      if (cartItem.map((item) => item.rewardId).includes(newItem.rewardId)) {
        const index = cartItem
          .map((item) => item.rewardId)
          .indexOf(newItem.rewardId);
        // console.log("idx " + index);
        // console.log(
        //   "prev qty: " +
        //     cartItem[index].quantity +
        //     " type " +
        //     typeof cartItem[index].quantity
        // );
        // console.log("current qty: " + quantity, " type " + typeof quantity);
        let newCart = [...cartItem];
        newCart[index].quantity =
          parseInt(cartItem[index].quantity) + parseInt(quantity);
        setCartItem(newCart);
      } else {
        setCartItem((cartItem) => [...cartItem, newItem]);
      }
     
      setClicked(false);
    }
  }, [clicked]);

  // useEffect(() => {
  //   console.log("cartID", item.id);
  //   console.log("cart", cartItem);
  // }, [cartItem]);

  return (
    <>
      <Box alignItems="center" m="10px">
        <Stack pt="5px" direction="column">
          <Grid
            gap={4}
            p="10px"
            templateColumns="repeat(6, 1fr)"
            templateRows="repeat(1, 1fr)"
          >
            <GridItem colSpan={1}>
              <Image boxSize="100px" src={item.imageUrl} />
            </GridItem>
            <GridItem colSpan={3}>
              <Heading size="md">{item.name}</Heading>
              <Text>{item.description}</Text>
              <Text>{item.cost + "G"}</Text>
            </GridItem>
            <GridItem colSpan={1} mt="2">
              Quantity:
              <NumberInput
                size="xs"
                onChange={setQuantity}
                maxW={16}
                step={1}
                defaultValue={0}
                min={0}
                max={10}
              >
                <NumberInputField />
                <NumberInputStepper>
                  <NumberIncrementStepper />
                  <NumberDecrementStepper />
                </NumberInputStepper>
              </NumberInput>
            </GridItem>
            <GridItem m="5" colSpan={1}>
              <IconButton onClick={() => setClicked(true)} icon={<AddIcon />} />
            </GridItem>
          </Grid>
        </Stack>
      </Box>
      <Divider />
    </>
  );
};

export default ShopReward;
