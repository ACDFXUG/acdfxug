module top (
    input clk,
    input rst_n,
    output reg [3:0] led
);

always @(posedge clk or negedge rst_n) begin
    if(~rst_n)
        led <= 4'h1;
    else
        led <= {led[2:0],led[3]};
end
endmodule
