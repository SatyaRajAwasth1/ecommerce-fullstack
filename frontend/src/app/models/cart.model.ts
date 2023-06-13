export interface Cart{
    items: Array<CartItem>
}

export interface CartItem {
    productImageLink: string;
    productId: number;
    productName: string;
    quantity: number;
    price: number;
    // category: string;

}
